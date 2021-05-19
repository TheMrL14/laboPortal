import React, {useEffect} from "react";
import {Button} from "primereact/button";
import {saveAs} from "file-saver";

import {BrowserQRCodeSvgWriter} from "@zxing/browser";
import SideNavDevice from "./SideNavDevice";

let svgElement;
let isDone = false;
const codeWriter = new BrowserQRCodeSvgWriter();

const DeviceCode = () => {
    useEffect(() => {
        isDone = false;
        const endPoint = location.pathname.substring(0, location.pathname.lastIndexOf('/'));
        svgElement = codeWriter.writeToDom("#qr-result", process.env.REACT_APP_URL + endPoint, 400, 400);
        isDone = true;
    });

    const downloadCode = () => {
        isDone = false;
        svgElement = document.querySelector("#qr-result > svg");
        const svgData = new XMLSerializer().serializeToString(svgElement);
        const blob = new Blob([svgData]);
        saveAs(blob, "device.svg");
        isDone = true;
    };

    return (
        <>
            <SideNavDevice/>
            <section className="mainContent">
                <article className="qr-content">
                    <pre>
                        <code id="qr-result"></code>
                    </pre>
                    <Button
                        label="Download Code"
                        className={isDone ? "p-disabled btn-primary" : "btn-primary"}
                        onClick={downloadCode}
                    />
                </article>

            </section>
        </>
    );
};

export default DeviceCode;
