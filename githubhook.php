<html><head><title>GitHub Post Recieve Hook</title></head><body>
<?php
	/*
	 * GitHub Post Recieve Hook in PHP
	 * By xythobuz
	 *
	 * Shows Commits of Github projects, sends emails to desired people.
	 * Installation:
	 * 1) Run the following 2 SQL Statements to create the needed tables
	 * 2) Enter your SQL Server credentials after the comments in this file.
	 * 3) Load this file onto your webserver
	 * 4) Point the Github Post Recieve Hook to this script.
	 */
	/*
	 * SQL to create table:

CREATE TABLE commits (
	id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
	name TEXT NOT NULL,
	mail TEXT,
	message TEXT,
	url TEXT NOT NULL,
	repo TEXT NOT NULL )

CREATE TABLE mail (
	id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
	mail TEXT NOT NULL,
	repo TEXT NOT NULL )

	 */

	$server = "SERVERNAME";
	$user = "USERNAME";
	$pass = "PASSWORD";
	$database = "DATABASE";

	$db = mysql_connect($server, $user, $pass);
	mysql_select_db($database);
	if (mysql_errno()) {
		echo ('Database error!');
		exit;
	}

	if (!array_key_exists('payload', $_POST)) {
		// Were not called as recieve hook --> Show stuff
?>
<p><a href="http://www.xythobuz.org">GutHub Post Recieve Hook in PHP by xythobuz</a></p>
<p>Repository:
<form method="get">
<? showSelect(); ?>
<input type="submit" value="Show commits">
</form></p>
<p>Mail Notifications:
<form method="get">
<? showSelect(0); ?>
<br><input type="text" name="mail">
<input type="submit" value="Subscribe Repo" name="subscribe">
</form></p>
<?
		if (isset($_GET['subscribe'])) {
			// Subscripe person
			if (($_GET['mail'] != "") && ($_GET['r'] != "")) {
				$sql = 'INSERT INTO mail(mail, repo)
				VALUES
					( "'.mysql_real_escape_string($_GET['mail']).'",
					"'.mysql_real_escape_string($_GET['r']).'" )';
				$result = mysql_query($sql);
				if (!$result) {
					echo "<p>Database error!</p></body></html>";
				} else {
					echo "<p>You are now on the list!</p></body></html>";
				}
			} else {
				echo "<p>Invalid data!</p></body></html>";
			}
			exit;
		} else {
			// Show commits
			if ((!isset($_GET['r'])) || ($_GET['r'] == "")) {
				$sql = 'SELECT *
				FROM commits
				ORDER BY id DESC';
			} else {
				$sql = 'SELECT *
				FROM commits
				WHERE repo = "'.mysql_real_escape_string($_GET['r']).'"
				ORDER BY id DESC';
			}
			$result = mysql_query($sql);
			if ($result) {
				echo "<h1>Commits</h1><hr>";
				while ($row = mysql_fetch_array($result)) {
					echo "<h2>".stripslashes($row['repo'])."</h2>";
					echo "<p>From: ".stripslashes($row['name'])." (".stripslashes($row['mail']).")</p>";
					echo "<p>".stripslashes($row['message'])."</p>";
					echo "<p><a href=\"".stripslashes($row['url'])."\">Go to commit in ".stripslashes($row['repo'])."</a></p>";
					echo "<hr>";
				}
				echo "</body></html>";
				exit;
			} else {
				echo "<p>Database error!</p><p>".mysql_error()."</p></body></html>";
				exit;
			}
		}
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
		$sql = 'INSERT INTO
			commits(name, mail, message, url, repo)
		VALUES
			("'.mysql_real_escape_string($name).'",
			"'.mysql_real_escape_string($from).'",
			"'.mysql_real_escape_string($msg).'",
			"'.mysql_real_escape_string($url).'",
			"'.mysql_real_escape_string($repo).'")';
		$result = mysql_query($sql);
		if (!$result) {
			echo "<p>Database error!</p><p>".mysql_error()."</p></body></html>";
			exit;
		}

		$sql = 'SELECT * FROM mail WHERE repo = "'.mysql_real_escape_string($repo).'"';
		$result = mysql_query($sql);
		if (!$result) {
			echo "<p>Database error!</p><p>".mysql_error()."</p></body></html>";
			exit;
		}
		$to = "";
		$i = 0;
		while ($row = mysql_fetch_array($result)) {
			if ($i++ != 0) {
				$to .= ", ";
			}
			$to .= stripslashes($row['mail']);
		}
		if ($to != "") {
			sendEmail($to, $from, $msg, $name, $url, $repo);
		}
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

	function showSelect($i = 1) {
?><select name="r">
<? if ($i == 1) { ?>
<option value="">All Repos</option>
<? }
		$sql = 'SELECT repo FROM commits GROUP BY repo';
		$result = mysql_query($sql);
		if (!$result) {
			echo "<p>Database error!</p><p>".mysql_error()."</p></body></html>";
			exit;
		}
		while ($row = mysql_fetch_array($result)) {
?><option value="<? echo stripslashes($row['repo']); ?>"<? if ($row['repo'] == $_GET['r']) { echo " selected"; } ?>><? echo stripslashes($row['repo']); ?></option><?
		}
?></select>
<?
	}
?>
</body></html>