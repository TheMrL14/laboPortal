import React, {useEffect, useState} from "react";
import {connect} from "react-redux";
import {loadDevices, saveDevice} from "../../redux/actions/deviceActions";
import {loadSops, saveSop} from "../../redux/actions/sopActions";
import PropTypes from "prop-types";
import SideNavDevices from "../devices/SideNavDevices";
import DeviceForm from "../device/DeviceForm";
import {newDevice, newSop} from "../../tools/Models";

import "../../style/devices.css";
import SideNavDevice from "./SideNavDevice";

import {withRouter} from "react-router";
import EditSopPage from "../sop/EditSopPage";

export function EditDevicePage({
                                   devices,
                                   loadDevices,
                                   saveDevice,
                                   sops,
                                   loadSops,
                                   saveSop,
                                   history,
                                   ...props
                               }) {
    const [isNewSop, setIsNewSop] = useState(false);
    const [device, setDevice] = useState({...props.device});
    const [sop, setSop] = useState({...props.device});
    const [errors, setErrors] = useState({});
    const [saving, setSaving] = useState(false);

    useEffect(() => {
        if (devices.length === 0) {
            loadDevices().catch((error) => {
                alert("Loading devices failed" + error);
            });
        } else {
            setDevice({...props.device});
            setSop({...props.device.sop});
        }

        if (sops.length === 0) {
            loadSops().catch((error) => {
                alert("Loading Sops failed" + error);
            });
        } else {
            setSop({...props.sop});
        }
    }, [props.device, props.sop]);

    function handleChange(event) {
        const {name, value} = event.target;
        setDevice((prevDevice) => ({
            ...prevDevice,
            [name]: name === "sopId" ? parseInt(value, 10) : value,
        }));
    }

    function formIsValid() {
        const {name, description} = device;
        const errors = {};

        if (!name) errors.title = "Title is required.";
        if (!description) errors.author = "Author is required";

        setErrors(errors);
        // Form is valid if the errors object still has no properties
        return Object.keys(errors).length === 0;
    }

    function handleSave(event) {
        event.preventDefault();

        if (!formIsValid()) return;
        setSaving(true);

        device.sop = sop;
        saveDevice(device)
            .then(() => {
                history.push("/devices");
            })
            .catch((error) => {
                setSaving(false);
                setErrors({onSave: error.message});
            });
    }

    function setSelectedSop(selected) {
        if (selected.id == 0) setIsNewSop(true);
        else setIsNewSop(false);
        setSop({...selected});
    }

    function handleSopSave(sop) {
        saveSop(sop)
            .then(() => {
                setIsNewSop(false);
            })
            .catch((error) => {
                setSaving(false);
                setErrors({onSave: error.message});
            });
    }

    function closeOverlay(e) {
        e.preventDefault();
        setIsNewSop(false);
        setSop(null);
    }

    const sopsToSelect = [...sops];
    sopsToSelect.unshift(newSop);
    return (
        <>
            {device.id ? <SideNavDevice/> : <SideNavDevices/>}
            <section className="mainContent">
                <DeviceForm
                    device={device}
                    sop={sop}
                    sops={sopsToSelect}
                    errors={errors}
                    onChange={handleChange}
                    onSave={handleSave}
                    setSelectedSop={setSelectedSop}
                    saving={saving}
                    isNewSop={isNewSop}
                />
                {isNewSop ? (
                    <section className="overlay">
                        <EditSopPage
                            sop={sop}
                            sops={sops}
                            isForm={true}
                            loadSops={loadSops}
                            saveSop={handleSopSave}
                            history={history}
                            closeWindow={closeOverlay}
                        />
                    </section>
                ) : null}
            </section>
        </>
    );
}

EditDevicePage.propTypes = {
    device: PropTypes.object.isRequired,
    devices: PropTypes.array.isRequired,

    loadDevices: PropTypes.func.isRequired,
    saveDevice: PropTypes.func.isRequired,

    sop: PropTypes.object,
    sops: PropTypes.array.isRequired,

    loadSops: PropTypes.func.isRequired,
    saveSop: PropTypes.func.isRequired,

    history: PropTypes.object.isRequired,
};

export function getDeviceBySlug(devices, slug) {
    return devices.find((device) => device.id === parseInt(slug)) || null;
}

function mapStateToProps(state, ownProps) {
    const slug = ownProps.match.params.slug;
    const device =
        slug && state.devices.length > 0
            ? getDeviceBySlug(state.devices, slug)
            : newDevice;

    const sop = device.sop == undefined ? newSop : device.sop;

    return {
        sop,
        sops: state.sops,
        device,
        devices: state.devices,
    };
}

const mapDispatchToProps = {
    loadDevices,
    saveDevice,
    loadSops,
    saveSop,
};


export default withRouter(
    connect(mapStateToProps, mapDispatchToProps)(EditDevicePage)
);