import React, {useEffect, useState} from "react";
import {connect} from "react-redux";
import {loadDevices} from "../../redux/actions/deviceActions";
import PropTypes from "prop-types";

import "../../style/device.css";
import SideNavDevice from "./SideNavDevice";
import {newDevice, newSop} from "../../tools/Models";
import SOPDetail from "../sop/SOPDetail";

export function DeviceSopPage({devices, loadDevices, ...props}) {
    const [device, setDevice] = useState({...props.device});
    const [sop, setSop] = useState({...props.sop});

    useEffect(() => {
        if (devices.length === 0) {
            loadDevices().catch((error) => {
                alert("Loading devices failed" + error);
            });
        } else {
            setDevice({...props.device});
            setSop({...props.sop});
        }
    }, [props.device, props.sop]);

    return devices.length === 0 ? (
        <h1>Loading</h1>
    ) : sop.id == null ? (
        <>
            <SideNavDevice/>
            <h1>add SOP</h1>
        </>
    ) : (
        <>
            <SideNavDevice/>
            <SOPDetail sop={sop}/>
        </>
    );
}

DeviceSopPage.propTypes = {
    device: PropTypes.object.isRequired,
    sop: PropTypes.object.isRequired,
    devices: PropTypes.array.isRequired,
    loadDevices: PropTypes.func.isRequired,
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
        device,
        devices: state.devices,
    };
}

const mapDispatchToProps = {
    loadDevices,
};

export default connect(mapStateToProps, mapDispatchToProps)(DeviceSopPage);
