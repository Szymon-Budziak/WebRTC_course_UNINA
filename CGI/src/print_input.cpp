#include <iostream>
#include <string>

using namespace std;

int main() {
    string s;
    if (!cin.eof())
        cin >> s;  // read first line of input data

    cout << "Content-Type: text/html" << endl;
    cout << endl;
    cout << "<HTML>" << endl;
    cout << "<HEAD>" << endl;
    cout << "  <TITLE>WebRTC class</TITLE>" << endl;
    cout << "</HEAD>" << endl;
    cout << "<BODY>" << endl;
    cout << "<H1>Hey...here is the query string:" << "</H1>" << endl;
    cout << "  <H2>QUERY_STRING: " << getenv("QUERY_STRING") << "</H2>" << endl;
    cout << "  <H1>And here follows the standard Input: " << s << "</H1>" << endl;
    cout << "</BODY>" << endl;
    cout << "</HTML>" << endl;

    return 0;
}

