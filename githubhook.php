<html><head><title>GitHub Post Recieve Hook</title></head><body>
<?php
	/*
	 * GitHub Post Recieve Hook in PHP
	 * Copy & Pasted from the interwebz by xythobuz
	 *
	 * Just enter some addresses after this comment.
	 * They'll get a mail for every commit!
	 * Also shows all commits if you enter your mysql credentials.
	 * Delete vars if you don't want this!
	 */
	/*
	 * SQL to create table
CREATE TABLE commits (
	id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
	name TEXT NOT NULL,
	mail TEXT,
	message TEXT,
	url TEXT NOT NULL,
	repo TEXT NOT NULL )
	 */
	$server = "HOSTNAME";
	$user = "USERNAME";
	$pass = "PASSWORD";
	$database = "DATABASE";

	$to = "test@test.com, test@example.org";

	if (isset($server) && isset($user) && isset($pass) && isset($database)) {
		$db = mysql_connect($server, $user, $pass);
		mysql_select_db($database);
		if (mysql_errno()) {
			echo ('Database error!');
			unset($server);
			unset($user);
			unset($pass);
			unset($database);
		}
	}

	if (!array_key_exists('payload', $_POST)) {
		if (isset($server) && isset($user) && isset($pass) && isset($database)) {
			if (!isset($_GET['r'])) {
				?><form method="get"><select name="r"><?
				$sql = 'SELECT repo FROM commits GROUP BY repo';
				$result = mysql_query($sql);
				if (!$result) {
					echo "<p>Database error!</p><p>".mysql_error()."</p></body></html>";
					exit;
				}
				while ($row = mysql_fetch_array($result)) {
					?><option value="<? echo $row['repo']; ?>"><? echo $row['repo']; ?></option><?
				}
				?></select></form><?
				
				$sql = 'SELECT *
					FROM commits
					ORDER BY id DESC';
				$result = mysql_query($sql);
				if ($result) {
					echo "<h1>Commits</h1><hr>";
					while ($row = mysql_fetch_array($result)) {
						echo "<h2>".$row['repo']."</h2>";
						echo "<p>From: ".$row['name']." (".$row['mail'].")</p>";
						echo "<p>".$row['message']."</p>";
						echo "<p><a href=\"".$row['url']."\">Go to commit in ".$row['repo']."</a></p>";
						echo "<hr>";
					}
					echo "</body></html>";
					exit;
				} else {
					echo "<p>Database error!</p><p>".mysql_error()."</p></body></html>";
					exit;
				}
			} else {
				$sql = 'SELECT *
					FROM commits
					WHERE repo = "'.mysql_real_escape_string($_GET['r']).'"
					ORDER BY id DESC';
				$result = mysql_query($sql);
				if ($result) {
					echo "<h1>Commits</h1><hr>";
					while ($row = mysql_fetch_array($result)) {
						echo "<h2>".$row['repo']."</h2>";
						echo "<p>From: ".$row['name']." (".$row['mail'].")</p>";
						echo "<p>".$row['message']."</p>";
						echo "<p><a href=\"".$row['url']."\">Go to commit in ".$row['repo']."</a></p>";
						echo "<hr>";
					}
					echo "</body></html>";
					exit;
				} else {
					echo "<p>Database error!</p><p>".mysql_error()."</p></body></html>";
					exit;
				}
			}
		} else {
			echo "<p>No payload!</p></body></html>";
		}
		exit;
	}
	
	extract(json_decode(stripslashes($_POST['payload']), true));

	if (!isset($commits) || !is_array($commits)) {
		echo "<p>Invalid Payload</p></body></html>";
		exit;
	}

	if (!isset($repository)) {
		echo "<p>Invalid Payload</p></body></html>";
		exit;
	}

	$repo = $repository['name'];

	foreach ($commits as $commit) {
		if (!array_key_exists('message', $commit))
			continue;

		$from = $commit['author']['email'];
		$msg = $commit['message'];
		$name = $commit['author']['name'];
		$url = $commit['url'];
		if (isset($server) && isset($user) && isset($pass) && isset($database)) {
			$sql = 'INSERT INTO
				commits(name, mail, message, url, repo)
			VALUES
				("'.$name.'",
				"'.$from.'",
				"'.$msg.'",
				"'.$url.'",
				"'.$repo.'")';
			$result = mysql_query($sql);
		}
		sendEmail($to, $from, $msg, $name, $url, $repo);
		echo "<p>Done!</p>\n";
	}

	function sendEmail($to, $from, $msg, $name, $url, $repo) {
		$mailtext = "<html><head><title>New Commit</title></head>";
		$mailtext .= "<body><h1>New Commit on ".$repo." Github Repo</h1>";
		$mailtext .= "<p>Message: ".$msg."</p>";
		$mailtext .= "<p>From ".$name."</p>";
		$mailtext .= "<p><a href=\"".$url."\">Go to commit</a></p>";
		$mailtext .= "</body></html>";
		
		$absender = $from;
		$betreff = "New ".$repo." Commit";
		$antwortan = $from;
		$header = "MIME-Version: 1.0\r\n";
		$header .= "Content-type: text/html; charset=iso-8859-1\r\n";
		$header .= "From: ".$absender."\r\n";
		$header .= "Reply-To: ".$antwortan."\r\n";
		$header .= "X-Mailer: PHP ".phpversion();
		mail( $to, $betreff, $mailtext, $header);
	}
?>
</body></html>