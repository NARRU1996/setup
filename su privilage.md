# making the user to include in superuser


1. $sudo visudo

    this cmd will make u to edit the file which can make users to became superuser
    
2 under the sudo file 3 parameters are there
     ------------------------------------------------------------------------------------------------------------------------------------------------------------   
     
            1P  ## Allow root to run any commands anywhere
                root    ALL=(ALL)       ALL
                narru   ALL=(ALL)       NOPASSWD: ALL ---------------------adding user hear will make user to access the sudo

                ## Allows members of the 'sys' group to run networking, software,
                ## service management apps and more.
                # %sys ALL = NETWORKING, SOFTWARE, SERVICES, STORAGE, DELEGATING, PROCESSES, LOCATE, DRIVERS

            2p  ## Allows people in group wheel to run all commands
                %wheel  ALL=(ALL)       ALL ---------------------adding user group hear will make group of users to access the sudo

            3p  ## Same thing without a password
                # %wheel        ALL=(ALL)       NOPASSWD: ALL
                narru   ALL=(ALL)       NOPASSWD: ALL---------------------adding user hear will make user to access the sudo without password 
                ansible  ALL=(ALL)      NOPASSWD: ALL
--------------------------------------------------------------------------------------------------------------------------------------------------------------------
