import React from "react";
import PropTypes from "prop-types";
import TextInput from "../common/input/TextInput";
import TextAreaInput from "../common/input/TextAreaInput";

import "../../style/form.css";
import {FetchByteArray} from "../common/Utils";
import StepInput from "../common/input/StepInput";
import AbbrInput from "../common/input/AbbrInput";

const SopForm = ({
                     sop,
                     onSave,
                     onChange,
                     onStepChange,
                     onStepAdd,
                     onStepRemove,
                     onAbbreviationChange,
                     onAbbreviationAdd,
                     onAbbreviationRemove,
                     closeWindow,
                     saving = false,
                     errors = {},
                 }) => {
    const setImage = (e, i) => {
        const file = e.files[0];
        let step = sop.procedure[i];
        step.imageName = file.name;
        FetchByteArray(file).then((fileByteArray) => (step.image = fileByteArray));
    };
    return (
        <>
            <form
                className="sopForm"
                onSubmit={onSave}
                style={closeWindow ? {width: "80%", padding: "2rem"} : {}}
            >
                <section className="top">
                    <h2>{sop.id ? "Edit" : "Add"} Sop (Standard Operating Procedures)</h2>
                    {closeWindow ? (
                        <button className="btn btn-delete" onClick={closeWindow}>
                            x
                        </button>
                    ) : null}
                </section>

                <TextInput
                    name="title"
                    label="Title"
                    value={sop.title}
                    onChange={onChange}
                    error={errors.title}
                />
                <TextAreaInput
                    name="description"
                    label="Description"
                    value={sop.description}
                    onChange={onChange}
                    error={errors.description}
                />
                <AbbrInput
                    name="Abbreviations"
                    steps={sop.abbreviations}
                    onStepChange={onAbbreviationChange}
                    onStepAdd={onAbbreviationAdd}
                    onStepRemove={onAbbreviationRemove}
                    setImage={setImage}
                />
                <StepInput
                    name="Procedure"
                    errors={errors}
                    steps={sop.procedure}
                    onStepChange={onStepChange}
                    onStepAdd={onStepAdd}
                    onStepRemove={onStepRemove}
                    setImage={setImage}
                />
                <button type="submit" disabled={saving} className="btn btn-primary">
                    {saving ? "Saving..." : "Save"}
                </button>
            </form>
        </>
    );
};

SopForm.propTypes = {
    sop: PropTypes.object.isRequired,
    errors: PropTypes.object,
    onSave: PropTypes.func.isRequired,
    onChange: PropTypes.func.isRequired,
    onStepChange: PropTypes.func.isRequired,
    onStepAdd: PropTypes.func.isRequired,

    onAbbreviationChange: PropTypes.func.isRequired,
    onAbbreviationAdd: PropTypes.func.isRequired,
    onAbbreviationRemove: PropTypes.func.isRequired,
    closeWindow: PropTypes.func,
    onStepRemove: PropTypes.func.isRequired,
    saving: PropTypes.bool,
};

export default SopForm;
