<?php
$username = "s2344401";
$password = "s2344401";
$database = "d2344401";
$link = mysqli_connect("127.0.0.1", $username, $password, $database);
$fname = $_REQUEST["fname"];
$lname = $_REQUEST["lname"];
$email = $_REQUEST["email"];
$pass = $_REQUEST["pass"];
$sql = "INSERT INTO STUDENT (STU_FNAME, STU_LNAME, STU_EMAIL, STU_PASSWORD) values ('$fname','$lname','$email','$pass')";
/* Insert into STUDENT table*/
if ($r = mysqli_query($link, $sql)) {
        echo "record added";
}else {
        echo "Error: " . $sql . "<br>" . mysqli_error($link);
}
mysqli_close($link);
?>