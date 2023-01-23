
<?php
$username = "s2344401";
$password = "s2344401";
$database = "d2344401";
$link = mysqli_connect("127.0.0.1", $username, $password, $database);
$assid = $_REQUEST["assid"];
$output=array();
/* Select queries return a resultset */
if ($result = mysqli_query($link,"Select * from GROUPS where ASS_ID = $assid")){
        while ($row=$result->fetch_assoc()){
                $output[]=$row;
        }
}
mysqli_close($link);
echo json_encode($output);
?>