![GitHub](https://img.shields.io/github/license/endreth/CMH?color=%2330c26c&style=flat-square) ![GitHub top language](https://img.shields.io/github/languages/top/endreth/CMH?color=%2330c26c&style=flat-square) ![GitHub language count](https://img.shields.io/github/languages/count/endreth/CMH?color=%2330c26c&style=flat-square) ![GitHub code size in bytes](https://img.shields.io/github/languages/code-size/endreth/CMH?color=%2330c26c&style=flat-square) ![GitHub repo size](https://img.shields.io/github/repo-size/endreth/CMH?color=%2330c26c&style=flat-square) ![GitHub file size in bytes](https://img.shields.io/github/size/endreth/CMH/cmh-1.0.jar?color=%2330c26c&style=flat-square)
# CMH - A Github Commit Map Hacker

**The CMH tool can populate the Github commit map with a non-random or random number of commits to improve its appearance. However, the tool is currently in a raw and unoptimized state.**

The tool utilizes JGit (EGit) to create a local repository, where a user can select any desired past date and create non-random or random commits. Once the commits are made, they are pushed to an empty private repository, which ensures that no one will see them. The commits will then appear on the user's contribution graph.

It's worth noting that this tool currently lacks any exception handling or notification capabilities, as it was created in a single night. Therefore, it's important to provide all information correctly, as any errors will require restarting the entire process.

## Usage
<img align="right" width="400" height="500" src="https://user-images.githubusercontent.com/104054427/228531736-3cbaf521-e9f9-4d54-8130-faa636aa8c3e.png">

Run the JAR file available [here](https://github.com/endreth/CMH/blob/master/cmh-1.0.jar):
```
$ java -jar cmh-1.0.jar
```

1. Create an empty private repository (Do **NOT** add Readme or License).
2. Generate a simple classic token for your profile:<br>
 * Go to your Settings
 * Go to <> Developer settings (bottom left)
 * Go to Personal access tokens / Tokens(classic)
 * Select Generate new token (classic)
 * Select any Expiration and 'repo' (full control) scope
3. Open the CMH tool:
 * Hit the 'Create local repo!' button
 * Select 'Randomize...' if you want random number of commits per day <br>(this will result in a more natural look in your commiting pattern)
 * Select the dates
 * Provide the necessary Github repo address, email<sup>1</sup> and token<sup>2</sup>
 * Hit the 'Fill Github commit map!' button
4. DONE!

### Notes
<sup>1</sup> The email address is mandatory, as only those commits that have an associated email address linked to your GitHub.com account will appear on your contributions graph. Read details [here](https://docs.github.com/en/account-and-profile/setting-up-and-managing-your-github-profile/managing-contribution-settings-on-your-profile/why-are-my-contributions-not-showing-up-on-my-profile).<br><br>
<sup>2</sup> Token-based authentication has succeeded email and password-based authentication, as described in the Github blog. On August 13, 2021, the blog stated that developers who currently use a password to authenticate Git operations with GitHub.com must begin using a personal access token. Read details [here](https://github.blog/2020-12-15-token-authentication-requirements-for-git-operations/#what-you-need-to-do-today).<br><br>
The FlatBee UI CSS theme used in the tool was designed by [Marc Schuler](https://github.com/marcschuler/FlatBee).
