import React from "react";
import PropTypes from "prop-types";
import {Tag} from "primereact/tag";

const TagsGroup = ({tags, label, onChange, placeholder, value, error}) => {
    let wrapperClass = "";
    if (error && error.length > 0) {
        wrapperClass += " " + "has-error";
    }

    const AbbreviationTag = (description, abbreviation) => (
        <Tag>
            <abbr title={description}>{abbreviation}</abbr>
        </Tag>
    )


    return (
        <div className={wrapperClass}>
            <label htmlFor={"Abbreviations"}>{label}</label>
            <div className="field" name="Abbreviations">
                {tags.map((tag) => {
                    AbbreviationTag(tag.description, tag.abbreviation)
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
