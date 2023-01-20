#!/system/bin/sh

# write log file if executable throws something at stdout/sterr
exec >>/data/media/0/watchdog.log 2>&1

# run script in background to avoid blocking boot chain
[ -n "$BG" ] || { BG=Y "$0" & exit; }

# try to ignore signals as much as possible
for i in $(seq 64); do trap '' "$i"; done

# execute script whenever exits e.g. when executable gets killed
trap "sleep 5; exec $0" EXIT

# execute the binary, should run in foreground, otherwise get in loop
echo "$(date): Starting program..."
/data/bin/watchdog com.diegulog.nativeservice com.diegulog.nativeservice com.diegulog.nativeservice.MainActivity 1 /data/media/0/

# program is killed, won't reach here if script is killed
echo "$(date): Re-executing script..."
