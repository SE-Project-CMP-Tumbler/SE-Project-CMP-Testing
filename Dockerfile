FROM cypress/included:8.7.0

# Update the dependencies to get the latest and greatest (and safest!) packages.
RUN apt update && apt upgrade -y
WORKDIR /testing

# avoid too many progress messages
# https://github.com/cypress-io/cypress/issues/1243
ENV CI=1

COPY ./Web/package.json ./Web/package.json

# install dependencies
RUN cd Web && npm install

# copy everything
COPY . /testing

RUN cd /testing
