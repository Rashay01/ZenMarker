
<?php
$username = "s2344401";
$password = "s2344401";
$database = "d2344401";
$link = mysqli_connect("127.0.0.1", $username, $password, $database);
$fname = $_REQUEST["fname"];
$lname = $_REQUEST["lname"];
$email = $_REQUEST["email"];
$pass = $_REQUEST["pass"];
$output=array();
/* Select queries return a resultset */
if ($result = mysqli_query($link, "select MAX(STU_ID) as MAXSTU_ID from STUDENT where STU_FNAME = '$fname' and STU_LNAME = '$lname' and STU_EMAIL = '$email' and STU_PASSWORD = '$pass' group by STU_FNAME, STU_LNAME, STU_EMAIL,STU_PASSWOR$while ($row=$result->fetch_assoc()){
$output[]=$row;
}
}
mysqli_close($link);
echo json_encode($output);
?>