<html><head><title>GitHub Post Recieve Hook</title></head><body>
<?php
	/*
	 * GitHub Post Recieve Hook in PHP
	 * Copy & Pasted from the interwebz by xythobuz
	 *
	 * Just enter some addresses after this comment.
	 * They'll get a mail for every commit!
	 */

	$to = "xythobuz@me.com, hutattedonmyarm@me.com, baeder.felix@gmail.com";

	if (!array_key_exists('payload', $_POST)) {
		echo "<p>No Payload</p></body></html>";
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
		sendEmail($to, $from, $msg, $name, $url, $repo);
		echo "<p>Mail sent!</p>\n";
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