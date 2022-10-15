# CGI dynamically rendered from C++ file

### Important note. All commands must be run as sudo

__1. Copy file to `/usr/lib/cgi-bin`:__

- _counter.cpp_ with _counter.txt_

```
cp src/counter.cpp /usr/lib/cgi-bin
cp src/counter.txt /usr/lib/cgi-bin
```

- _print_input.cpp_

```
cp src/print_input.cpp /usr/lib/cgi-bin
```

__2. Compile file:__

- _counter.cpp_

```
g++ -o /usr/lib/cgi-bin/counter /usr/lib/cgi-bin/counter.cpp
```

- _print_input.cpp_

```
g++ -o /usr/lib/cgi-bin/print_input /usr/lib/cgi-bin/print_input.cpp
```

__3. Change permission of the files:__

- _counter.cpp_

```
chmod 755 /usr/lib/cgi-bin/counter
chmod 757 /usr/lib/cgi-bin/counter.txt
```

- _print_input.cpp_

```
chmod 755 /usr/lib/cgi-bin/print_input
```

__4. Restart apache server and check its status (it should be active):__

```
systemctl restart apache2
systemctl status apache2
```

__5. Run dynamic server on your browser:__

- _counter.cpp_

When you refresh the browser, the `Number of accesses` should increment by 1 everytime you refresh it

```
http://localhost/cgi-bin/counter
```

- _print_input.cpp_

```
http://localhost/cgi-bin/print_input?name=Test
```