import React, {useEffect, useState} from "react";
import {connect} from "react-redux";
import {deleteDevice, loadDevices, saveDevice} from "../../redux/actions/deviceActions";
import {loadSops, saveSop} from "../../redux/actions/sopActions";
import PropTypes from "prop-types";
import SideNavDevices from "../devices/SideNavDevices";
import DeviceForm from "../device/DeviceForm";
import {newDevice, newSop} from "../../tools/Models";

import "../../style/devices.css";
import SideNavDevice from "./SideNavDevice";

import {withRouter} from "react-router";
import EditSopPage from "../sop/EditSopPage";
import {Dialog} from "primereact/dialog";
import {Button} from "primereact/button";

export function EditDevicePage({
                                   devices,
                                   loadDevices,
                                   saveDevice,
                                   deleteDevice,
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

    const [sopsToSelect, setSopsToSelect] = useState(sops);


    useEffect(() => {
        if (devices.length === 0) {
            loadDevices().catch((error) => {
                alert("Loading devices failed" + error);
            });
        } else {
            setDevice({...props.device});

        }
    }, [props.device]);

    useEffect(() => {
        if (sops.length === 0) {
            loadSops().catch((error) => {
                alert("Loading sops failed" + error);
            });
        } else {
            const loadedSops = [...sops];
            loadedSops.unshift(newSop);
            setSopsToSelect(loadedSops)
            setSop({...props.sop});
        }
    }, [props.sopsToSelect, props.sop])


    function handleChange(event) {
        const {name, value} = event.target;
        setDevice((prevDevice) => ({
            ...prevDevice,
            [name]: name === "sopId" ? parseInt(value, 10) : value,
        }));
    }

    const handleLinkChange = (e, index) => {
        const {value} = e.target;
        const list = [...device.externalLinks];
        list[index] = value;
        setDevice((prevDevice) => ({
            ...prevDevice,
            ["externalLinks"]: list,
        }));
    };

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
                props.sop = sop;
            })
            .catch((error) => {
                setSaving(false);
                setErrors({onSave: error.message});
            });
    }

    function handleDeleteDevice(e) {
        e.preventDefault();
        deleteDevice(device);
        history.push("/devices");

    }

    function setSelectedSop(selected) {
        console.log(selected)
        if (selected.id == 0) setIsNewSop(true);
        else setIsNewSop(false);
        setSop({...selected});
    }

    function handleSopSave(sop) {
        closeOverlay()
        saveSop(sop)
            .catch((error) => {
                setSaving(false);
                setErrors({onSave: error.message});
            });
    }

    function closeOverlay() {
        setIsNewSop(false);
        setSop(null);
    }

    function addExternalLink() {
        setDevice((prevDevice) => ({
            ...prevDevice,
            ["externalLinks"]: [
                ...prevDevice.externalLinks, ""
            ],
        }));
    }

    function removeExternalLink(index) {
        const list = [...device.externalLinks];
        list.splice(index, 1);
        setDevice((prevDevice) => ({
            ...prevDevice,
            ["externalLinks"]: list,
        }));
    }


    return (

        <>
            {device.id ? <SideNavDevice/> : <SideNavDevices/>}
            <section className="mainContent">
                {device.id ? <Button className="p-button-danger" label="Delete" icon="pi pi-trash"
                                     onClick={(event => handleDeleteDevice(event))}/> : null}
                <DeviceForm
                    device={device}
                    sop={sop}
                    sops={sopsToSelect}
                    errors={errors}
                    onChange={handleChange}
                    onLinksChange={handleLinkChange}
                    handleAddLinkClick={addExternalLink}
                    handleRemoveLinkClick={removeExternalLink}
                    onSave={handleSave}
                    setSelectedSop={setSelectedSop}
                    saving={saving}
                    isNewSop={isNewSop}
                />
                <Dialog visible={isNewSop} onHide={() => closeOverlay()} on modal>
                    <EditSopPage
                        sop={sop}
                        sops={sops}
                        isForm={true}
                        loadSops={loadSops}
                        handleSaveSop={handleSopSave}
                        history={history}
                        closeWindow={closeOverlay}
                    />
                </Dialog>
            </section>
        </>
    );
}

EditDevicePage.propTypes = {
    device: PropTypes.object.isRequired,
    devices: PropTypes.array.isRequired,

    loadDevices: PropTypes.func.isRequired,
    saveDevice: PropTypes.func.isRequired,
    deleteDevice: PropTypes.func.isRequired,

    sop: PropTypes.object,
    sops: PropTypes.array.isRequired,
    sopsToSelect: PropTypes.array,

    loadSops: PropTypes.func.isRequired,
    saveSop: PropTypes.func.isRequired,

    history: PropTypes.object.isRequired,
};

export function getDeviceBySlug(devices, slug) {
    return devices.find((device) => device.id === parseInt(slug)) || newDevice;
}

function mapStateToProps(state, ownProps) {
    const slug = ownProps.match.params.slug;
    const device =
        slug && state.devices.length > 0
            ? getDeviceBySlug(state.devices, slug)
            : newDevice;

    const sop = device.sop == undefined ? newSop : device.sop;
    device.externalLinks = device.externalLinks.length === 0 ? [""] : device.externalLinks;
    return {
        sop,
        sops: state.sops,
        sopsToSelect: state.sops,
        device,
        devices: state.devices,
    };
}

const mapDispatchToProps = {
    loadDevices,
    saveDevice,
    loadSops,
    saveSop,
    deleteDevice,
};


export default withRouter(
    connect(mapStateToProps, mapDispatchToProps)(EditDevicePage)
);