const linkDeleteUser = "http://localhost:8080/admin/delete/";

function deletedUser(id) {
    fetch(linkDeleteUser + id, {
        method: "DELETE",
    }).then(() => allUsers());
}