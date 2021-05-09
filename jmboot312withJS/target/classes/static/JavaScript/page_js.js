$(document).ready(function () {
    restartAllUser();
    $('.AddBtn').on('click', function (event) {
        let user = {
            name: $("#name").val(),
            lastname: $("#lastname").val(),
            email: $("#email").val(),
            login: $("#login").val(),
            password: $("#password").val(),
            rolesSet: getRole("#selectRole")

        }
        console.log(user);
        fetch("api/new_user", {
            method: "POST",
            headers: {
                "Content-Type": "application/json;charset=utf-8"
            },
            body: JSON.stringify(user)
        }).then(() => openTabById('nav-home'))
            .then(() => restartAllUser());
        $('input').val('');
    });
});

function createTableRow(u) {  //
    let userRole = "";
    for (let i = 0; i < u.rolesSet.length; i++) {
        userRole += " " + u.rolesSet[i].nameRoles;
    }
    return `<tr id="user_table_row">
            <td>${u.id}</td>
            <td>${u.name}</td>
            <td>${u.lastname}</td>
            <td>${u.email}</td>
            <td>${u.login}</td>
            <td>${userRole}</td>
            <td>
            <a  href="/api/${u.id}" class="btn btn-info eBtn" >Edit</a>
            </td>
            <td>
            <a  href="/api/delete/${u.id}" class="btn btn-danger delBtn">Delete</a>
            </td>
        </tr>`;
}

function getRole(address) {
    let data = [];
    $(address).find("option:selected").each(function () {
        data.push({id: $(this).val(), rolesSet: $(this).attr("name"), authority: $(this).attr("name")})
    });
    return data;
}

function restartAllUser() {
    let UserTableBody = $("#users_table")

    UserTableBody.children().remove();

    fetch("api/all_users")
        .then((response) => {
            response.json().then(data => data.forEach(function (item, i, data) {
                let TableRow = createTableRow(item);
                UserTableBody.append(TableRow);

            }));
        }).catch(error => {
        console.log(error);
    });
}

document.addEventListener('click', function (event) {
    event.preventDefault()
    if ($(event.target).hasClass('delBtn')) {
        let href = $(event.target).attr("href");
        delModalButton(href)
    }



    if ($(event.target).hasClass('eBtn')) {
        let href = $(event.target).attr("href");
        $(".editUser #exampleModal").modal();

        $.get(href, function (user) {
            $(".editUser #id").val(user.id);
            $(".editUser #nameEd").val(user.name);
            $(".editUser #lastnameEd").val(user.lastname);
            $(".editUser #emailEd").val(user.email);
            $(".editUser #loginEd").val(user.login);
            $(".editUser #passwordEd").val(user.password);
            $(".editUser #selectRoleEd").val(user.rolesSet);
        });
    }
    if ($(event.target).hasClass('editButton')) {
        let user = {
            id:$('#id').val(),
            name:$('#nameEd').val(),
            lastname:$('#lastnameEd').val(),
            email:$('#emailEd').val(),
            login:$('#loginEd').val(),
            password:$('#passwordEd').val(),
            rolesSet: getRole("#selectRoleEd")

        }
        editModalButton(user)
        console.log(user);
    }

    if ($(event.target).hasClass('logout')) {
        logout();
    }
    if ($(event.target).hasClass('btnUserTable')) {
        userTable();
    }

});

function delModalButton(href) {
    fetch(href, {
        method: "DELETE",
        headers: {
            "Content-Type": "application/json;charset=utf-8"
        }
    }).then(() => restartAllUser());
}
function editModalButton(user) {
    fetch("api/edit", {
        method: "PUT",
        headers: {
            "Content-Type": "application/json;charset=utf-8"
        },
        body: JSON.stringify(user)
    }).then(function (response) {
        $('input').val('');
        $('.editUser #exampleModal').modal('hide');
        restartAllUser();
    })
}
function openTabById(tab) {
    $('.nav-tabs a[href="#' + tab + '"]').tab('show');
}
function logout(){
    document.location.replace("/logout");
}
function userTable(){
    document.location.replace("/user");
}
