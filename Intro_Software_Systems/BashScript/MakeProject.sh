#!/bin/bash
if [ $# = 0 ]
then
  echo "Usage: $0 name"
  exit 1
fi

cd /home/2017/aweber8
if [ ! -d project ]
then
  mkdir project
fi

cd project
if [ ! -d cs206 ]
then
   mkdir cs206
fi

cd cs206
if [ -d $1 ]
then
   echo "This project name has already been used."
   exit 1
else
   mkdir -p ./$1/archive ./$1/backup ./$1/docs
   mkdir  ./$1/assets ./$1/database ./$1/source
fi

cd $1/source
echo -e "#!/bin/bash\ncp *.h ../backup\ncp *.c ../backup" > backup.sh
chmod +x backup.sh

echo "Your project directories have been created."
exit 0
