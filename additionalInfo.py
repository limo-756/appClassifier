from urllib.request import urlopen
from urllib.error import HTTPError
from bs4 import BeautifulSoup
import sys
# html = urlopen("https://play.google.com/store/apps/details?id=com.rovio.angrybirdsspace.ads&hl=en")
# html = urlopen("https://play.google.com/store/apps/details?id=com.rovio.angrybirdsspace.ads&hl=en")
# "ids=com.kiloo.subwaysurf&xhr=1&hl=en" https://play.google.com/store/apps/details?id=com.uc.browser.en&hl=en
# curl --data "ids=com.uc.browser.en&xhr=1&hl=en" https://play.google.com/store/xhr/getdoc?authuser=0 --silent
# curl --data "ids=com.whatsapp&xhr=1&hl=en" https://play.google.com/store/xhr/getdoc?authuser=0 --silent > 4.txt
# curl --data "ids=com.facebook.orca&xhr=1&hl=en" https://play.google.com/store/xhr/getdoc?authuser=0 --silent > 5.txt
html = urlopen(sys.argv[1])
bsObj = BeautifulSoup(html.read())
s = bsObj.findAll("div",{"class":"details-wrapper","class":"details-section metadata"})
s = s[0]
s= str(s)
b1 = BeautifulSoup(s)
# print(b1.prettify())
dict ={"Updated":None,"Installs":None,"Current Version":None,"Requires Android":None,"In-app Products":None,"Offered By":None,"Developer":None}
dict["physical-address"] = None
dict["email"] = None
dict["visit website"] = None
t = b1.find("div",{"class":"details-section-contents"})
for child in t.children:
    if(child.name is not None):
            a = child.find("div",{"class":"title"})
            b = child.find("div",{"class":"content"})
            if(a is not None and b is not None):
                if(a.string is not None and b.string is not None):
                    if(dict.__contains__(str( a.string.strip() ))):
                        dict[a.string.strip()]=b.string.strip()
            if(a is not None and a.string.strip() in "Developer"):
                for dev in a.next_siblings:
                    if(dev.name is not None):
                        physicalAddress = dev.find("div",{"class":"physical-address"})
                        if(physicalAddress.name is not None):
                            dict["physical-address"] = physicalAddress.string.strip()
                        links = dev.findAll("a")
                        for link in links:
                            if(link.name is not None):
                                innerContent = link.string.strip().lower()
                                if(innerContent is not None):
                                    if("email" in innerContent):
                                        dict["email"] = innerContent.split()[1]
                                    if("visit" in innerContent and "website" in innerContent):
                                        dict["visit website"] = link["href"]

for k in dict:
    print(str(k) + " : " + str(dict[k]))

# s = bsObj.findAll("div",{"class":"permissions-container","class":"bucket-style"})
# s = s[0]
# s= str(s)