import React from "react";
import PropTypes from "prop-types";

import {Tag} from "primereact/tag";
import "../../style/sop.css";

const SOPDetail = (props) => {
    return (
        <article className="procedure mainContent">
            <section className="sopHeader">
                <h2>{props.sop.title}</h2>

                {props.sop.abbreviations.map((tag, i) => (
                    <abbr className="p-mr-2" key={i} title={tag.description}>
                        <Tag value={tag.abbreviationShort}/>
                    </abbr>
                ))}

                <p>{props.sop.description}</p>
            </section>

            <ul>
                {props.sop.procedure.map((step, i) => (
                    <li key={i}>
                        {step.image ? (
                            <img
                                className="imgStep"
                                src={`data:image/jpeg;base64,${step.image}`}
                            ></img>
                        ) : null}
                        {step.stepType == "STEP" ? (<div className="step item">
                                <p className="number">{step.stepNr}</p>
                                <p>{step.message}</p>
                            </div>) :
                            (<div className="subHeader">
                                <p>{step.message}</p>
                            </div>)}


                    </li>
                ))}
            </ul>
        </article>
    );
};

export default SOPDetail;

SOPDetail.propTypes = {
    sop: PropTypes.object.isRequired,
};
