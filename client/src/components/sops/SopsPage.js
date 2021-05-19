import React, {useEffect, useState} from "react";
import {connect} from "react-redux";
import {useHistory} from "react-router-dom";
import PropTypes from "prop-types";

import {loadSops} from "../../redux/actions/sopActions";

import SideNavSops from "./SideNavSops";

import "../../style/sops.css";
import {DataTable} from "primereact/datatable";
import {Column} from "primereact/column";
import {Dropdown} from "primereact/dropdown";

/*
 *css and html structuire from
 *https://codepen.io/faaezahmd/pen/dJeRex
 *
 */

function SopsPage({sops, loadSops, ...props}) {
    const history = useHistory();


    const [filteredSops, setFilteredSops] = useState(sops);
    const [typeFilter, setTypeFilter] = useState('');

    const statuses = [
        '', 'DEVICE', 'INDEPENDENT'
    ]


    useEffect(() => {
        if (sops.length === 0) {
            loadSops().catch((error) => {
                alert("Loading courses failed" + error);
            });
        } else {
            filter()
        }
    }, [props.filteredSops]);


    useEffect(() => {
        filter()

    }, [typeFilter])

    function filter() {
        let sopsToSelect = [...sops];
        sopsToSelect.forEach((sop) => {
            //add authors
            sop.authorsString = sop.authors.map((author) => {
                return author.firstName + " " + author.lastName;
            });

        });
        sopsToSelect = sopsToSelect.filter(sop => sop.type.indexOf(typeFilter) > -1)
        setFilteredSops(sopsToSelect)
    }

    function onRowSelect(event) {
        const id = event.data.id;

        history.push("./sops/" + id + "/info");
    }


    function onStatusChange(e) {

        setTypeFilter(e.value)
    }

    const statusFilter = <Dropdown value={typeFilter} options={statuses} onChange={onStatusChange}
                                   placeholder="Select a Type" className="p-column-filter"/>;
    return (
        <>
            <SideNavSops/>
            <section className="mainContent">
                <DataTable
                    value={filteredSops}
                    onRowSelect={onRowSelect}
                    selectionMode="single"
                    dataKey="id"
                    emptyMessage="No sops found."
                >
                    <Column field="title" filter header="Title"></Column>
                    <Column field="authorsString" filter header="Authors"></Column>
                    <Column field="creationDate" filter header="Creation Date"></Column>
                    <Column field="type" header="Type" filter filterElement={statusFilter}/>
                </DataTable>
            </section>
        </>
    );
}

SopsPage.propTypes = {
    sops: PropTypes.array.isRequired,
    filteredSops: PropTypes.array.isRequired,
    loadSops: PropTypes.func.isRequired,
};

const mapStateToProps = (state) => ({
    sops: state.sops,
    filteredSops: state.sops
});

const mapDispatchToProps = {
    loadSops,
};

export default connect(mapStateToProps, mapDispatchToProps)(SopsPage);
