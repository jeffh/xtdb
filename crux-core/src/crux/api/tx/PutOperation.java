package crux.api.tx;

import crux.api.CruxDocument;

import java.util.Date;
import java.util.Objects;

public final class PutOperation extends TransactionOperation {
    public static PutOperation create(CruxDocument document) {
        return new PutOperation(document, null, null);
    }

    public static PutOperation create(CruxDocument document, Date startValidTime) {
        return new PutOperation(document, startValidTime, null);
    }

    public static PutOperation create(CruxDocument document, Date startValidTime, Date endValidTime) {
        return new PutOperation(document, startValidTime, endValidTime);
    }

    public CruxDocument getDocument() {
        return document;
    }

    public Date getStartValidTime() {
        return startValidTime;
    }

    public Date getEndValidTime() {
        return endValidTime;
    }

    private final CruxDocument document;
    private final Date startValidTime;
    private final Date endValidTime;

    private PutOperation(CruxDocument document, Date startValidTime, Date endValidTime) {
        this.document = document;
        this.startValidTime = startValidTime;
        this.endValidTime = endValidTime;
    }

    @Override
    public <E> E accept(Visitor<E> visitor) {
        return visitor.visit(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PutOperation that = (PutOperation) o;
        return document.equals(that.document)
                && Objects.equals(startValidTime, that.startValidTime)
                && Objects.equals(endValidTime, that.endValidTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash("put", document, startValidTime, endValidTime);
    }
}
