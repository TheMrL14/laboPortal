import React, {useEffect} from "react";
import {connect} from "react-redux";
import {useHistory} from "react-router-dom";
import PropTypes from "prop-types";

import {loadSops} from "../../redux/actions/sopActions";

import SideNavSops from "./SideNavSops";

import "../../style/sops.css";
import {DataTable} from "primereact/datatable";
import {Column} from "primereact/column";

/*
 *css and html structuire from
 *https://codepen.io/faaezahmd/pen/dJeRex
 *
 */

function SopsPage({sops, loadSops}) {
    const history = useHistory();

    useEffect(() => {
        if (sops.length === 0) {
            loadSops().catch((error) => {
                alert("Loading courses failed" + error);
            });
        }
    }, []);

    function onRowSelect(event) {
        const id = event.data.id;

        history.push("./sops/" + id + "/info");
    }

    const sopsToSelect = [...sops];
    if (sops.length > 0) {
        sopsToSelect.forEach((sop) => {
            sop.authorsString = sop.authors.map((author) => {
                return author.firstName + " " + author.lastName;
            });
        });
    }
    return (
        <>
            <SideNavSops/>
            <section className="mainContent">
                <DataTable
                    value={sopsToSelect}
                    onRowSelect={onRowSelect}
                    selectionMode="single"
                    dataKey="id"
                >
                    <Column field="title" header="Title"></Column>
                    <Column field="authorsString" ch header="Authors"></Column>
                    <Column field="creationDate" header="Creation Date"></Column>
                </DataTable>
            </section>
        </>
    );
}

SopsPage.propTypes = {
    sops: PropTypes.array.isRequired,
    loadSops: PropTypes.func.isRequired,
};

const mapStateToProps = (state) => ({
    sops: state.sops,
});

const mapDispatchToProps = {
    loadSops,
};

export default connect(mapStateToProps, mapDispatchToProps)(SopsPage);
