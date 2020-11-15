<?php   

	include("connect.php");

	$product_id = $_POST['product_id'];
	$name = $_POST['name'];
	$detail = $_POST['detail'];
	$price = $_POST['price'];
	$image = $_POST['image']; 

	$sql = " INSERT INTO product (id_product, name, detail, price, img_url) 
			 VALUES ('$product_id', '$name', '$detail', '$price', '$image')";   

	$result = mysqli_query($con, $sql);	
	
	mysqli_close($con); 
?>  