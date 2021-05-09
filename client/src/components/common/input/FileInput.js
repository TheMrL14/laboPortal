import React from "react";
import PropTypes from "prop-types";
import {FileUpload} from "primereact/fileupload";

const FileInput = ({name, label, value, setImage, error}) => {
    let wrapperClass = "form-group";
    if (error && error.length > 0) {
        wrapperClass += " " + "has-error";
    }

    return (
        <div className={wrapperClass}>
            <label htmlFor={name}>{label}</label>
            <div className="field">
                <FileUpload
                    mode="basic"
                    name={name}
                    value={value}
                    accept="image/*"
                    maxFileSize={2000000}
                    onSelect={setImage}
                />
                {error && <div className="alert alert-danger">{error}</div>}
            </div>
        </div>
    );
};

FileInput.propTypes = {
    name: PropTypes.string.isRequired,
    label: PropTypes.string.isRequired,
    setImage: PropTypes.func.isRequired,
    placeholder: PropTypes.string,
    value: PropTypes.string,
    error: PropTypes.string,
};

export default FileInput;
