ssh authorization

$sudo vi /etc/ssh/sshd_config
   set
   #passwordauthentication yes ------> passwordauthentication yes

   :wq   ---->exit with saving

$systemctl restart sshd_config
    restart the sshd it will make the canges to be mounted

$cd /../.../.../...       ---> change to directory where u want to store keys
                                usually it is /home/user
$ssh-keygen  -----> genarates .ssh directory with id_rsa && id_rsa.pub

$ssh-copy-id -i username@ip  ---->copy the id_rsa.pub to .ssh/authorized_keys
                                    of ssh reciver vm
---to do manual
login to reciver machine
$cd ~
$mkdir .ssh
$cd .ssh
$ vi authorized_keys
    copy the public key text hear and save
$systemctl restart sshd
$ssh from master machine...............