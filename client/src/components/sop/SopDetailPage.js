import React, {useEffect, useState} from "react";
import {connect} from "react-redux";
import {loadSops, saveSop} from "../../redux/actions/sopActions";
import PropTypes from "prop-types";
import SideNavSop from "./SideNavSop";
import {newSop} from "../../tools/Models";
import SOPDetail from "./SOPDetail";

function SopDetailPage({sops, loadSops, ...props}) {
    const [sop, setSop] = useState({...props.sop});

    useEffect(() => {
        if (sops.length === 0) {
            loadSops().catch((error) => {
                alert("Loading sops failed" + error);
            });
        } else {
            setSop({...props.sop});
        }
    }, [props.sop]);

    return sops.length === 0 ? (
        <h1>Loading</h1>
    ) : (
        <>
            <SideNavSop/>
            <SOPDetail sop={sop}/>
        </>
    );
}

SopDetailPage.propTypes = {
    sop: PropTypes.object.isRequired,
    sops: PropTypes.array.isRequired,
    loadSops: PropTypes.func.isRequired,
};

export function getSopBySlug(sops, slug) {
    return sops.find((sop) => sop.id === parseInt(slug)) || null;
}

function mapStateToProps(state, ownProps) {
    const slug = ownProps.match.params.slug;
    const sop =
        slug && state.sops.length > 0 ? getSopBySlug(state.sops, slug) : newSop;
    return {
        sop,
        sops: state.sops,
    };
}

const mapDispatchToProps = {
    loadSops,
    saveSop,
};

export default connect(mapStateToProps, mapDispatchToProps)(SopDetailPage);
