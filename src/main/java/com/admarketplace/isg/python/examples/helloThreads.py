import threading
import datetime
import time

class ThreadClass(threading.Thread):
  def run(self):
    now = datetime.datetime.now()
    print ("%s says Hello World at time: %s" % 
    (self.getName(), now))

start = time.time()
for i in range(200):
  t = ThreadClass()
  t.start()

print ("Elapsed Time: %s" % (time.time() - start))
