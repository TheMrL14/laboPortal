const newDevice = {
    id: null,
    metaInfo: "",
    name: "",
    description: "",
    image: "",
    imageName: "",
    externalLinks: [],
    videos: []
};

const newStep = {
    stepNr: 1,
    message: "",
};

const newAbbreviation = {
    abbreviation: "",
    description: "",
};
const newSop = {
    id: 0,
    title: "New Sop",
    description: "",
    creationDate: "",
    procedure: [newStep],
    abbreviations: [newAbbreviation],
};

module.exports = {
    newDevice,
    newSop,
    newStep,
    newAbbreviation,
};
