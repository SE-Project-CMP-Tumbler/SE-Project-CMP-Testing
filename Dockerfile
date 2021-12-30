FROM cypress/base

WORKDIR /testing

# avoid too many progress messages
# https://github.com/cypress-io/cypress/issues/1243
ENV CI=1

COPY ./Web/package.json ./Web/package.json

# install dependencies
RUN cd Web && npm install

# verify installation
RUN npx cypress verify

# copy everything
COPY . /testing

RUN cd /testing
