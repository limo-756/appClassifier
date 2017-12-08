from androguard.core.bytecodes.apk import APK
from androguard.core.bytecodes.dvm import DalvikVMFormat
from androguard.core.analysis.analysis import Analysis
from androguard.decompiler.decompiler import DecompilerJADX
from androguard.core.androconf import show_logging
import logging
import sys

# Enable log output
# show_logging(level=logging.DEBUG)

# Load our example APK
a = APK(sys.argv[1])

# Create DalvikVMFormat Object
d = DalvikVMFormat(a)
# Create Analysis Object
dx = Analysis(d)

# Load the decompiler
# Set the path to the jadx executable!
# decompiler = DecompilerJADX(d, dx, jadx="/home/anurag/git/jadx-0.6.1/bin/jadx")

# propagate decompiler and analysis back to DalvikVMFormat
# d.set_decompiler(decompiler)
# d.set_vmanalysis(dx)

# Now you can do stuff like:
# for m in d.get_methods()[:10]:
#     print(m)
# print(decompiler.get_source_method(m))
print a.get_app_name()
for i in a.get_permissions():
    print i