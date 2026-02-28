# Step 1: Nginx use karo
FROM nginx:stable-alpine

# Step 2: Browser folder ka content copy karo
# (Dhyan rakhna, GitHub folder mein 'browser' folder ke ANDAR ki files honi chahiye)
COPY . /usr/share/nginx/html

# Step 3: Routing Fix (Taki page refresh par 404 na aaye)
RUN printf "server { \n\
    listen 80; \n\
    location / { \n\
        root /usr/share/nginx/html; \n\
        index index.html index.htm; \n\
        try_files \$uri \$uri/ /index.html; \n\
    } \n\
}" > /etc/nginx/conf.d/default.conf

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]