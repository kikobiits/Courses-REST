let loadCoursesButton = document.getElementById('loadCourses')

loadCoursesButton.addEventListener('click', onLoadCourses);

function onLoadCourses(event) {

    event.preventDefault();

    var requestOptions = {
        method: 'GET',
        redirect: 'follow'
    };

    let courseContainer = document.getElementById('courses-container');
    courseContainer.innerHTML = '';

    fetch("http://localhost:8080/api/courses", requestOptions)
        .then(response => response.json())
        .then(json => json.forEach(course => {

            let row = document.createElement('tr');

            let titleCol = document.createElement('td');
            let lecturerCol = document.createElement('td');
            let roomCol = document.createElement('td');

            titleCol.textContent = course.title;
            lecturerCol.textContent = course.lecturer;
            roomCol.textContent = course.room;

            row.appendChild(titleCol);
            row.appendChild(lecturerCol);
            row.appendChild(roomCol);

            courseContainer.appendChild(row);

        }))
        .catch(error => console.log('error', error));
}

// function to target the form's contents

document.querySelector('#my-form').addEventListener('submit', function () {
    event.preventDefault();

    var title = event.target.elements.title.value;
    var room = event.target.elements.room.value;
    var lecturer = event.target.elements.lecturer.value;

    // class for making a request

    let xhr = new XMLHttpRequest();
    xhr.open("POST", "http://localhost:8080/api/courses/submit", true);

    // declare request headers for content type json

    xhr.setRequestHeader("Accept", "application/json");
    xhr.setRequestHeader("Content-Type", "application/json");

    // creating json object

    var data = {
        "title": title,
        "room": room,
        "lecturer": lecturer
    };

    xhr.send(JSON.stringify(data));
})