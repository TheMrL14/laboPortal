import React from "react";
import PropTypes from "prop-types";
import {Tag} from "primereact/tag";

const TagsGroup = ({tags, label, onChange, placeholder, value, error}) => {
    let wrapperClass = "";
    if (error && error.length > 0) {
        wrapperClass += " " + "has-error";
    }

    return (
        <div className={wrapperClass}>
            <label htmlFor={"Abbreviations"}>{label}</label>
            <div className="field" name="Abbreviations">
                {tags.map((tag) => {
                    <Tag>
                        <abbr title={tag.description}>{tag.abbreviation}</abbr>
                    </Tag>;
                })}

                {error && <div className="alert alert-danger">{error}</div>}
            </div>
        </div>
    );
};

TagsGroup.propTypes = {
    tags: PropTypes.array.isRequired,
    label: PropTypes.string.isRequired,
    onChange: PropTypes.func.isRequired,
    placeholder: PropTypes.string,
    value: PropTypes.string,
    error: PropTypes.string,
};

export default TagsGroup;
