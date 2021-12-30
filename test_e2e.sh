# write the tests driver here, also make sure to download any needed
# utilities before hand, and delete these utilities later on.
# test other teams' work.

# clean old reports
rm -rf cypress/reports

# make a new mocha reports dir
mkdir -p cypress/reports/mochareports

# useless LOC
rm -rf ./reports

# make a new reports dir
mkdir -p reports

# run tests
npx cypress run --quiet --config "baseUrl=http://web.dev.tumbler.social" 

# combine-reports
mochawesome-merge cypress/reports/mocha/*.json > cypress/reports/mochareports/report.json

# generate-report
marge cypress/reports/mochareports/report.json -f report -o cypress/reports/mochareports

# move report to report folder
mv cypress/reports/mochareports/ reports/

# rename html
mv reports/report.html reports/index.html

exit 0
