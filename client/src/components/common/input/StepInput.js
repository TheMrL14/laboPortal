import React from "react";
import PropTypes from "prop-types";
import FileInputIcon from "./FileInputIcon";
import {InputText} from "primereact/inputtext";

const StepInput = ({
                       steps,
                       name,
                       label,
                       errors,
                       onStepChange,
                       onStepAdd,
                       onStepRemove,
                       hasImage,
                       setImage,
                   }) => {
    let wrapperClass = "form-group";

    return (
        <div className={wrapperClass}>
            <label>{label}</label>

            <section className="">
                {steps.map((step, i) => (
                    <div key={i} className="p-inputgroup">
                        <InputText
                            onChange={(e) => onStepChange(e, i)}
                            name={name}
                            className="form-control"
                            value={step}
                        />
                        {steps.length !== 1 && (
                            <span
                                onClick={() => onStepRemove(i)}
                                className="p-inputgroup-addon danger"
                            >
                -
              </span>
                        )}
                        {steps.length - 1 === i && (
                            <span onClick={onStepAdd} className="p-inputgroup-addon">
                +
              </span>
                        )}
                        {hasImage ? (
                            <FileInputIcon
                                name="image"
                                label="Add Image"
                                value={i.imageName}
                                setImage={(e) => setImage(e, i)}
                                error={errors.description}/>
                        ) : null}
                        <br/>
                    </div>
                ))}
            </section>
        </div>
    );
};

StepInput.propTypes = {
    steps: PropTypes.array.isRequired,
    name: PropTypes.string.isRequired,
    errors: PropTypes.object,
    setImage: PropTypes.func.isRequired,
    hasImage: PropTypes.bool.isRequired,
    onStepChange: PropTypes.func.isRequired,
    onStepAdd: PropTypes.func.isRequired,
    onStepRemove: PropTypes.func.isRequired,
};

export default StepInput;
