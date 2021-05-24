import React from "react";
import PropTypes from "prop-types";
import {FileUpload} from "primereact/fileupload";

const FilesInput = ({name, label, value, setFiles, error}) => {
    let wrapperClass = "form-group";
    if (error && error.length > 0) {
        wrapperClass += " " + "has-error";
    }
    const chooseOptions = {label: 'Add Videos', icon: 'pi pi-fw pi-plus', className: 'p-button-success'};
    return (
        <div className={wrapperClass}>
            <label htmlFor={name}>{label}</label>
            <div className="field">
                <FileUpload
                    chooseOptions={chooseOptions}
                    name={name + "[]"}
                    value={value}
                    accept="video/*"
                    maxFileSize={1000000000}
                    uploadHandler={setFiles}
                    showButtons={false}
                    auto
                    customUpload
                    multiple
                />
                {error && <div className="alert alert-danger">{error}</div>}
            </div>
        </div>
    );
};

FilesInput.propTypes = {
    name: PropTypes.string.isRequired,
    label: PropTypes.string.isRequired,
    setFiles: PropTypes.func.isRequired,
    placeholder: PropTypes.string,
    value: PropTypes.string,
    error: PropTypes.string,
};

export default FilesInput;
