import React from "react";
import PropTypes from "prop-types";
import TextInput from "../common/input/TextInput";
import TextAreaInput from "../common/input/TextAreaInput";
import {Dropdown} from "primereact/dropdown";
import FileInput from "../common/input/FileInput";
import {FetchByteArray} from "../common/Utils";
import StepInput from "../common/input/StepInput";

const DeviceForm = ({
                        device,
                        sop,
                        sops,
                        onSave,
                        onChange,
                        onLinksChange,
                        handleAddLinkClick,
                        handleRemoveLinkClick,
                        isNewSop,
                        setSelectedSop,
                        saving = false,
                        errors = {},
                    }) => {
    const setImage = (e) => {
        const file = e.files[0];
        device.imageName = file.name;
        FetchByteArray(file).then(
            (fileByteArray) => (device.image = fileByteArray)
        );
    };

    if (sop == null || sop.id == 0) {
        sop = null;
    }

    return (
        <form onSubmit={onSave}>
            <h2>{device.id ? "Edit" : "Add"} Device</h2>
            {errors.onSave && (
                <div className="alert alert-danger" role="alert">
                    {errors.onSave}
                </div>
            )}
            <TextInput
                name="name"
                label="Name"
                value={device.name}
                onChange={onChange}
                error={errors.title}
            />
            <TextAreaInput
                name="description"
                label="Description"
                value={device.description}
                onChange={onChange}
                error={errors.description}
            />
            <FileInput
                name="image"
                label="Image"
                value={device.imageName}
                setImage={setImage}
                error={errors.description}
            />
            <StepInput
                name="externalLinks"
                label="External Links"
                errors={errors}
                steps={device.externalLinks}
                onStepChange={onLinksChange}
                onStepAdd={handleAddLinkClick}
                onStepRemove={handleRemoveLinkClick}
                hasImage={false}
                setImage={setImage}
            />
            <Dropdown
                optionLabel="title"
                filter
                filterBy="title"
                placeholder="Select a SOP"
                dataKey="id"
                value={sop}
                options={sops}
                onChange={(e) => setSelectedSop(e.value)}
            />


            <br/>
            {isNewSop ? null : (
                <button type="submit" disabled={saving} className="btn btn-primary">
                    {saving ? "Saving..." : "Save"}
                </button>
            )}
        </form>
    );
};

DeviceForm.propTypes = {
    activeIndex: PropTypes.number,
    isNewSop: PropTypes.bool,
    device: PropTypes.object.isRequired,
    sop: PropTypes.object,
    sops: PropTypes.array.isRequired,
    errors: PropTypes.object,
    onSave: PropTypes.func.isRequired,
    onChange: PropTypes.func.isRequired,
    setSelectedSop: PropTypes.func.isRequired,
    saving: PropTypes.bool,
};

export default DeviceForm;
