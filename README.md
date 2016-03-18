# Rapture-JMeter

Task | Rapture API | Comment
:---  | :--- | :---
Create the Document repository with versioning | None | The configuration is String based
Define the URI (or address) of the document repository | None | The URI is string based
Call createDocRepo(String docRepoUri, String config) method to create the repository | createDocRepo(String docRepoUri, String config) |
Check the repo exists and Assert on the Boolean response | docRepoExists(String docRepoUri) |
Load a json file (from /resources) from disk and create a Rapture document | * putDoc(String docUri, String content) * getDocAndMeta(String docUri) |
Load a slightly different json and write to same document URI and check the version was updated | |
use JsonPatch to get the difference between the two documents | |
