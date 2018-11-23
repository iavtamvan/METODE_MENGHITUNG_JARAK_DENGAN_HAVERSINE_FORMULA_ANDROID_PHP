<?php 
	include_once "koneksi.php";
	
	$lat = $_GET['lat'];
	$lng = $_GET['lng'];	
	
	/* 10.0.2.2 adalah IP Address localhost EMULATOR ANDROID STUDIO,
        Ganti IP Address tersebut dengan IP Laptop Apabila di RUN di HP. HP dan Laptop harus 1 jaringan */
	$url = "http://10.0.2.2/android/haversine/images/";
	
	// perhitungan haversine formula pada sintak SQL
	$query = mysqli_query($con, "SELECT id, nama, gambar, (6371 * ACOS(SIN(RADIANS(lat)) * SIN(RADIANS($lat)) + COS(RADIANS(lng - $lng)) * COS(RADIANS(lat)) * COS(RADIANS($lat)))) AS jarak FROM wisata HAVING jarak < 6371 ORDER BY jarak ASC");
	
	$json = array();
	
	$no = 0;
	
	while($row = mysqli_fetch_assoc($query)){
		$json[$no]['id'] = $row['id'];
		$json[$no]['nama'] = $row['nama'];
		$json[$no]['gambar'] = $url.$row['gambar'];
		$json[$no]['jarak'] = $row['jarak'];
		
		$no++;
	}
	
	echo json_encode($json);
	
	mysqli_close($con);
	
?>