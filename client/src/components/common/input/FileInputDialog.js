import React from 'react';
import {Dialog} from "primereact/dialog";
import {FileUpload} from "primereact/fileupload";
import {importSop} from "../../../api/sopApi";

const FileInputDialog = (props) => {


    function uploadFile(event) {
        importSop(event.files[0]).then(r => props.onHide())

    }

    return (
        <Dialog
            header="Import Pdf File"
            width='350px'
            modal={true}
            visible={props.visible}
            onHide={props.onHide}
        >
            <FileUpload
                customUpload={true}
                uploadHandler={uploadFile}
                mode="basic"
                auto={true}
                chooseLabel="Upload PDF Sop"
                name="PdfFileUpload"
                accept="pdf"
                maxFileSize={2000000}
            />
        </Dialog>
    );
};

export default FileInputDialog;
