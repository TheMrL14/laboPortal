import React from "react";
import PropTypes from "prop-types";
import {FileUpload} from "primereact/fileupload";

const FileInputIcon = ({name, value, setImage, error}) => {
    return (
        <>
            <FileUpload
                mode="basic"
                name={name}
                value={value}
                accept="image/*"
                maxFileSize={2000000}
                onSelect={setImage}
            />
            {error && <div className="alert alert-danger">{error}</div>}
        </>
    );
};

FileInputIcon.propTypes = {
    name: PropTypes.string.isRequired,
    label: PropTypes.string.isRequired,
    setImage: PropTypes.func.isRequired,
    placeholder: PropTypes.string,
    value: PropTypes.string,
    error: PropTypes.string,
};

export default FileInputIcon;
