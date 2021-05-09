import React, {useEffect, useState} from "react";
import {connect} from "react-redux";
import {loadDevices, saveDevice} from "../../redux/actions/deviceActions";
import PropTypes from "prop-types";
import {newDevice} from "../../tools/Models";

import "../../style/devices.css";
import SideNavDevice from "./SideNavDevice";
import {withRouter} from "react-router";


function DetailDevicePage({devices, loadDevices, ...props}) {
    const [device, setDevice] = useState({...props.device});

    useEffect(() => {
        if (devices.length === 0) {
            loadDevices().catch((error) => {
                alert("Loading devices failed" + error);
            });
        } else {
            setDevice({...props.device});
        }

    }, [props.device]);
    return (
        <>
            <SideNavDevice/>
            <section className="mainContent">
                <h1>{device.name}</h1>
                {device.image != null ? (
                    <img
                        className="imgStep"
                        src={`data:image/jpeg;base64,${device.image}`}
                    ></img>
                ) : null}
                <p>{(device.description != null) ? device.description : device.sop.description}</p>
            </section>

        </>
    );
}

DetailDevicePage.propTypes = {
    device: PropTypes.object.isRequired,
    devices: PropTypes.array.isRequired,

    loadDevices: PropTypes.func.isRequired,
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


    return {
        device,
        devices: state.devices,
    };
}

const mapDispatchToProps = {
    loadDevices,
    saveDevice,
};

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(DetailDevicePage));
