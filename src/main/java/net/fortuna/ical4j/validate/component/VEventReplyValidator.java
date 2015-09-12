package net.fortuna.ical4j.validate.component;

import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.validate.ComponentValidator;
import net.fortuna.ical4j.validate.PropertyValidator;
import net.fortuna.ical4j.validate.ValidationException;
import net.fortuna.ical4j.validate.Validator;

/**
 * METHOD:REPLY Validator.
 *
 * <pre>
 * Component/Property  Presence
 * ------------------- ----------------------------------------------
 * METHOD              1       MUST be "REPLY"
 * VEVENT              1+      All components MUST have the same UID
 *     ATTENDEE        1       MUST be the address of the Attendee
 *                             replying.
 *     DTSTAMP         1
 *     ORGANIZER       1
 *     RECURRENCE-ID   0 or 1  only if referring to an instance of a
 *                             recurring calendar component.  Otherwise
 *                             it must NOT be present.
 *     UID             1       MUST be the UID of the original REQUEST
 *
 *     SEQUENCE        0 or 1  MUST if non-zero, MUST be the sequence
 *                             number of the original REQUEST. MAY be
 *                             present if 0.
 *
 *     ATTACH          0+
 *     CATEGORIES      0 or 1  This property may contain a list of values
 *     CLASS           0 or 1
 *     COMMENT         0 or 1
 *     CONTACT         0+
 *     CREATED         0 or 1
 *     DESCRIPTION     0 or 1
 *     DTEND           0 or 1  if present DURATION MUST NOT be present
 *     DTSTART         0 or 1
 *     DURATION        0 or 1  if present DTEND MUST NOT be present
 *     EXDATE          0+
 *     EXRULE          0+
 *     GEO             0 or 1
 *     LAST-MODIFIED   0 or 1
 *     LOCATION        0 or 1
 *     PRIORITY        0 or 1
 *     RDATE           0+
 *     RELATED-TO      0+
 *     RESOURCES       0 or 1  This property MAY contain a list of values
 *     REQUEST-STATUS  0+
 *     RRULE           0+
 *     STATUS          0 or 1
 *     SUMMARY         0 or 1
 *     TRANSP          0 or 1
 *     URL             0 or 1
 *     X-PROPERTY      0+
 *
 * VTIMEZONE           0 or 1 MUST be present if any date/time refers
 *                            to a timezone
 * X-COMPONENT         0+
 *
 * VALARM              0
 * VFREEBUSY           0
 * VJOURNAL            0
 * VTODO               0
 * </pre>
 *
 */
public class VEventReplyValidator implements Validator<VEvent> {

    private static final long serialVersionUID = 1L;

    public void validate(VEvent target) throws ValidationException {
        PropertyValidator.getInstance().assertOne(Property.ATTENDEE, target.getProperties());
        PropertyValidator.getInstance().assertOne(Property.DTSTAMP, target.getProperties());
        PropertyValidator.getInstance().assertOne(Property.ORGANIZER, target.getProperties());
        PropertyValidator.getInstance().assertOne(Property.UID, target.getProperties());

        PropertyValidator.getInstance().assertOneOrLess(Property.RECURRENCE_ID, target.getProperties());
        PropertyValidator.getInstance().assertOneOrLess(Property.SEQUENCE, target.getProperties());
        PropertyValidator.getInstance().assertOneOrLess(Property.CATEGORIES, target.getProperties());
        PropertyValidator.getInstance().assertOneOrLess(Property.CLASS, target.getProperties());
        PropertyValidator.getInstance().assertOneOrLess(Property.CREATED, target.getProperties());
        PropertyValidator.getInstance().assertOneOrLess(Property.DESCRIPTION, target.getProperties());
        PropertyValidator.getInstance().assertOneOrLess(Property.DTEND, target.getProperties());
        PropertyValidator.getInstance().assertOneOrLess(Property.DTSTART, target.getProperties());
        PropertyValidator.getInstance().assertOneOrLess(Property.DURATION, target.getProperties());
        PropertyValidator.getInstance().assertOneOrLess(Property.GEO, target.getProperties());
        PropertyValidator.getInstance().assertOneOrLess(Property.LAST_MODIFIED, target.getProperties());
        PropertyValidator.getInstance().assertOneOrLess(Property.LOCATION, target.getProperties());
        PropertyValidator.getInstance().assertOneOrLess(Property.PRIORITY, target.getProperties());
        PropertyValidator.getInstance().assertOneOrLess(Property.RESOURCES, target.getProperties());
        PropertyValidator.getInstance().assertOneOrLess(Property.STATUS, target.getProperties());
        PropertyValidator.getInstance().assertOneOrLess(Property.SUMMARY, target.getProperties());
        PropertyValidator.getInstance().assertOneOrLess(Property.TRANSP, target.getProperties());
        PropertyValidator.getInstance().assertOneOrLess(Property.URL, target.getProperties());

        ComponentValidator.assertNone(Component.VALARM, target.getAlarms());
    }
}