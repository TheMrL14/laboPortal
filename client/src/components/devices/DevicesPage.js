import React, {useEffect, useState} from "react";
import {connect} from "react-redux";
import PropTypes from "prop-types";

import {loadDevices} from "../../redux/actions/deviceActions";

import DeviceTile from "./DeviceTile";
import SideNavDevices from "./SideNavDevices";
import "../../style/devices.css";
import {InputText} from "primereact/inputtext";

function DevicesPage({devices, loadDevices, ...props}) {

    const [filteredDevices, setFilteredDevices] = useState(devices);

    useEffect(() => {
        if (devices.length === 0) {
            loadDevices().catch((error) => {
                alert("Loading devices failed" + error);
            });
        } else {
            setFilteredDevices(props.filteredDevices)
        }
    }, [props.filteredDevices]);


    const handleSearch = (event) => {
        let value = event.target.value.toLowerCase();
        let result = [];
        result = devices.filter(device => device.name.toLowerCase().indexOf(value) > -1);

        setFilteredDevices(result);

    }


    return (
        <>
            <SideNavDevices/>
            <section className="mainContent">
                <div className="searchBar">
                    <span className="p-input-icon-left">
                        <i className="pi pi-search"/>
                        <InputText onChange={(e) => handleSearch(e)} placeholder="Search"/>
                    </span>
                </div>
                <section className="p-grid">
                    {filteredDevices.map((device) => (
                        <DeviceTile key={device.name} device={device}/>
                    ))}
                </section>
            </section>
        </>
    );
}

DevicesPage.propTypes = {
    devices: PropTypes.array.isRequired,
    loadDevices: PropTypes.func.isRequired,
};

const mapStateToProps = (state) => ({
    devices: state.devices,
    filteredDevices: state.devices
});
const mapDispatchToProps = {
    loadDevices,
};

export default connect(mapStateToProps, mapDispatchToProps)(DevicesPage);
