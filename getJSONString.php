<?php
$host = "localhost";
$username = "root";
$password = "";
$dbname = "cafixturedb";
$table = 'teams_table';

// connect to mysql server
mysql_connect($host, $username, $password) or die('Could not connect');

mysql_select_db($dbname); // select the db name

$sql = "Select * from " . $table; // enter your sql query
$result = mysql_query($sql); // Gets table details

$temp = array(); // Creates temp array variable

// Adds each records/row to $temp
while($row=mysql_fetch_assoc($result)) {
    $temp[] = $row;
}
$struct = array("Teams" => $temp);
// Formats json from temp and shows/print on page
echo json_encode($struct);
?>