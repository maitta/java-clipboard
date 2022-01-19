# JavaClipboard

This program will walk recursively through all the files under the specified seed and will copy their paths and contents into an encrypted text file that can then be copied into a clipboard.

This encrypted file can then be decrypted and used to restore the original file structure and its content into a different location.

## Use Case

It can be useful in case one has access to a remote machine from which there isn't internet access and file downloads or drag and drop to the local machine are disabled and the only way to copy to or from the local machine is by means of the clipboard.

## How to Use

Run it as you would run any other java cli program without parameters which are to be set within the app.config file.

### Good to Know

- What parameters can be used and/or need to be set are explained in the app.release.config
- The file app.release.config needs to be renamed to app.config before the first launch
- Empty directories are omitted
- Text-based files only, binaries are ignored
- Command to copy the file name to the clipboard
  > echo .\readme.txt | clip
- Command to copy the file content to the clipboard
  > type .\readme.txt | clip

