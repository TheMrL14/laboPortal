import React from "react";
import PropTypes from "prop-types";
import {InputText} from "primereact/inputtext";

const AbbrInput = ({steps, name, onStepChange, onStepAdd, onStepRemove}) => {
    let wrapperClass = "form-group";

    return (
        <div className={wrapperClass}>
            <label>{name}</label>

            {steps.map((step, i) => (
                <article className="p-grid p-fluid" key={i}>
                    <div className="p-col-10 p-md-2">
                        <div className="p-inputgroup">
                            <InputText
                                onChange={(e) => onStepChange(e, i)}
                                name="abbreviation"
                                className="form-control"
                                placeholder="Abbreviation"
                                value={step.abbreviation}
                            />
                        </div>
                    </div>
                    <div className="p-col-12 p-md-10">
                        <div className="p-inputgroup">
                            <InputText
                                placeholder="Description"
                                onChange={(e) => onStepChange(e, i)}
                                name="description"
                                className="form-control"
                                value={step.description}
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
                        </div>
                    </div>
                </article>
            ))}
        </div>
    );
};

AbbrInput.propTypes = {
    steps: PropTypes.array.isRequired,
    name: PropTypes.string.isRequired,
    errors: PropTypes.object,
    setImage: PropTypes.func.isRequired,

    onStepChange: PropTypes.func.isRequired,
    onStepAdd: PropTypes.func.isRequired,
    onStepRemove: PropTypes.func.isRequired,
};

export default AbbrInput;
