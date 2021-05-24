import React from "react";
import PropTypes from "prop-types";

const DeviceTile = (props) => {


    const bgColor = [
        "--third-color",
        "--fourth-color",
        "--fifth-color",
        "--sixth-color",
        " --seventh-color"
    ];

    const randomBgColor = bgColor[(props.device.name.length * new Date().getHours()) % bgColor.length];
    return (
        <article className="p-col-6 p-md-6 p-lg-4 p-xl-4 p-xxl">
            <a href={"./devices/" + props.device.id + "/info"} className="card">
                <div
                    className="thumb"
                    style={{
                        backgroundColor: 'var(' + randomBgColor + ')',
                        backgroundImage: `url(data:image/jpeg;base64,${props.device.image})`
                    }}
                />
                <article>
                    <h1>{props.device.name}</h1>
                </article>
            </a>
        </article>
    );
};
DeviceTile.propTypes = {
    device: PropTypes.object.isRequired,
};

export default DeviceTile;
