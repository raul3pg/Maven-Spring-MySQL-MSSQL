function redirectToAddArtist() {
    window.location = "/index/artist/new";
}

function redirectToAddTrack() {
    window.location = "/index/track/new";
}

function deleteArtist(id){
    window.location = "index/artist/" + id + "/delete";
}

function deleteTrack(id){
    window.location = "index/track/" + id + "/delete";
}