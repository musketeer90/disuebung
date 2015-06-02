import java.io.File;
import java.util.ArrayList;

public class RecoveryManager {

	private String directory;

	RecoveryManager(String directory) {

		this.directory = directory;

	}

	public void redo(int pagenumber) {

		ArrayList<LogData> logData = new ArrayList<LogData>();
		UserData currentUserData = new UserData(0, 0, 0, "");

		File dir = new File(directory);
		File[] filesList = dir.listFiles();
		for (File file : filesList) {
			if (file.isFile()) {
				String filename = file.getName();
				if (filename.contains("UD")) {
					String[] filenamesplit = filename.split("_");
					int pageID = Integer.parseInt(filenamesplit[4]);
					int lsn = Integer.parseInt(filenamesplit[6].split("\\.")[0]);
					int taid = Integer.parseInt(filenamesplit[2]);

					FileRead fr = new FileRead(filename);
					if (currentUserData.getLsn() < lsn && pagenumber == pageID) {
						currentUserData = new UserData(pageID, lsn, taid,
								fr.readData());
					}

				}

			}
		}
		System.out.println("Newest UserData File: TAID: " + currentUserData.getTaid() + " LSN: " + currentUserData.getLsn());
		for (File file : filesList) {
			if (file.isFile()) {

				String filename = file.getName();
				if (filename.contains("LD")) {

					FileRead fr = new FileRead(filename);
					String[] filenamesplit = filename.split("_");
					int pageID = Integer.parseInt(filenamesplit[4]);
					int lsn = Integer.parseInt(filenamesplit[6].split("\\.")[0]);
					int taid = Integer.parseInt(filenamesplit[2]);


					if (currentUserData.getLsn() < lsn && pagenumber == pageID) {
						UserData ud = new UserData(pageID, lsn, taid,
								fr.readData());
						System.out
								.println("Found LogData that is not persistent as UserData yet: "
										+ filename);
						Buffer.writeUD(pageID, ud);
					}
				}

			}
		}
	}

}
