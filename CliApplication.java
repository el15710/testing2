package snippet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CliApplication {

	public static void main(String[] args) throws IOException {
		URL url;
		String time = " ";
		String base_url = "http://localhost:8765/energy/api/";
		if ((args.length >= 1) && (args[0].equals("energy_groupXXXX"))) {
			// System.out.print(args.length);
			if (args.length >= 2) {
				switch (args[1]) {
				case "Admin":
					System.out.print("Hello Admin");
					break;
				case "ActualvsForecast":
				case "DayAheadTotalLoadForecast":
				case "ActualTotalLoad": {
					if (((args.length >= 4) && args[2].equals("--area"))) {
						if (((args.length >= 6) && args[4].equals("--timeres"))) {
							if (args.length >= 8) {
								switch (args[6]) {
								case "--date":
									time = "date";
									break;
								case "--month":
									time = "month";
									break;
								case "--year":
									time = "year";
									break;
								default:
									System.out.println("Missing requested parameter: [--date | --month | --year]");
								}
								base_url += args[1] + "/" + args[3] + "/" + args[5] + "/" + time + "/" + args[7];
								url = new URL(base_url);
								System.out.print(url);
								HttpURLConnection con = (HttpURLConnection) url.openConnection();
								con.setRequestMethod("GET");
								// int status = con.getResponseCode();
								BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
								String inputLine;
								StringBuffer content = new StringBuffer();
								while ((inputLine = in.readLine()) != null) {
									content.append(inputLine);
								}
								System.out.print(content);
								in.close();
							} else
								System.out.println("Missing requested parameter: [--date | --month | --year]");
						} else
							System.out.println("Missing requested parameter: --timeres");
					} else
						System.out.println("Missing requested parameter: --area");
					break;
				}
				case "AggregatedGenerationPerType": {
					if (((args.length >= 4) && args[2].equals("--area"))) {
						if (((args.length >= 6) && args[4].equals("--timeres"))) {
							if (((args.length >= 8) && args[6].equals("--productiontype"))) {
								if (args.length >= 10) {
									switch (args[8]) {
									case "--date":
										time = "date";
										break;
									case "--month":
										time = "month";
										break;
									case "--year":
										time = "year";
										break;
									default:
										System.out.println("Missing requested parameter: [--date | --month | --year]");
									}
									base_url += args[1] + "/" + args[3] + "/" + args[7] + "/" + args[5] + "/" + time
											+ "/" + args[9];
									url = new URL(base_url);
									System.out.print(url);
									HttpURLConnection con = (HttpURLConnection) url.openConnection();
									con.setRequestMethod("GET");
									// int status = con.getResponseCode();
									BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
									String inputLine;
									StringBuffer content = new StringBuffer();
									while ((inputLine = in.readLine()) != null) {
										content.append(inputLine);
									}
									System.out.println(content);
									in.close();
								} else
									System.out.println("Missing requested parameter: [--date | --month | --year]");
							} else
								System.out.println("Missing requester parameter: --productiontype");
						} else
							System.out.println("Missing requested parameter: --timeres");
					} else
						System.out.println("Missing requested parameter: --area");
					break;
				}
				case "HealthCheck": {
					base_url += args[1];
					url = new URL(base_url);
					System.out.print(url);
					HttpURLConnection con = (HttpURLConnection) url.openConnection();
					con.setRequestMethod("GET");
					break;
				}
				case "Reset": {
					base_url += args[1];
					url = new URL(base_url);
					System.out.print(url);
					HttpURLConnection con = (HttpURLConnection) url.openConnection();
					con.setRequestMethod("POST");
					break;
				}
				case "Login": {
					if (((args.length >= 4) && args[2].equals("--username"))) {
						if (((args.length >= 6) && args[4].equals("--passw"))) {
							base_url += args[1] + "/" + args[2] + "/" + args[4];
							url = new URL(base_url);
							System.out.print(url);
							HttpURLConnection con = (HttpURLConnection) url.openConnection();
							con.setRequestMethod("POST");
						} else
							System.out.println("Missing requester parameter: --passw");
					} else
						System.out.println("Missing requested parameter: --username");
					break;
				}
				case "Logout": {
					base_url += args[1];
					url = new URL(base_url);
					System.out.print(url);
					HttpURLConnection con = (HttpURLConnection) url.openConnection();
					con.setRequestMethod("POST");
					break;
				}
				default:
					System.out.print("Missing SCOPE");
				}
			} else
				System.out.print("Missing energy_groupXXXX");
		}
	}
}
