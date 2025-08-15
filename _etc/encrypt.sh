CWD=$(echo $(realpath "${0}") | xargs dirname)
#echo "${CWD}"
source "${CWD}"/_env-loader.sh

find ../ -type f -regextype posix-extended \
  -regex "^(../|./|/).*(_env-).*.properties$" \
  -or -regex "^(../|./|/).*(\.rsync).*" \
  -not -regex ".*.gpg$" \
  -not -regex ".*_etc.*" \
  | xargs -i gpg -v --passphrase $GPG_PASSPHRASE --batch --yes -o {}.gpg -c {}