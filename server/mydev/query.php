<?php   

	include("connect.php");

	$json_return = array();
	$sql = "SELECT * FROM product";   
	$result = mysqli_query($con, $sql);

	while($row = mysqli_fetch_assoc($result)){   
		array_push($json_return, array(
			"id" => $row["id"],
			"id_product" => $row["id_product"],
			"name" => $row["name"],
			"detail" => $row["detail"],
			"price" => $row["price"],
			"img_product" => $row["img_url"] ));
	}	
	
	print json_encode($json_return);
	
	mysqli_close($con);
?>  